package com.aim.nova.hub.services

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


/**
 * Represents an ADB device.
 * @param id The unique identifier of the device.
 * @param state The current state of the device (e.g., "device", "offline", "unauthorized").
 */
data class AdbDevice(
    val id: String,
    val state: String
)

/**
 * Sealed class to represent the result of fetching ADB devices.
 * It can either be a successful list of devices or an error with a message.
 */
sealed class DeviceResult {
    /**
     * Represents a successful retrieval of ADB devices.
     * @param devices A list of [AdbDevice] objects.
     */
    data class Success(val devices: List<AdbDevice>) : DeviceResult()

    /**
     * Represents an error during the retrieval of ADB devices.
     * @param message A descriptive error message.
     */
    data class Error(val message: String) : DeviceResult()
}

/**
 * Interface for a service that can retrieve ADB device information.
 */
interface IDeviceService {
    /**
     * Retrieves a list of connected ADB devices.
     * @return A [DeviceResult] indicating success with a list of devices or an error.
     */
    suspend fun getAdbDevices(): DeviceResult
}

/**
 * Desktop implementation of [DeviceService] that executes the 'adb devices' command.
 */
class DesktopDeviceService : IDeviceService {

    /**
     * Executes the 'adb devices' command and parses its output to return a list of devices.
     * Handles various error scenarios, including 'adb' not found, command execution errors,
     * and parsing issues.
     *
     * @return A [DeviceResult] indicating success with a list of devices or an error.
     */
    override suspend fun getAdbDevices(): DeviceResult {
        return try {
            // Build the process to execute 'adb devices'
            val process = ProcessBuilder("adb", "devices", "-l")
                .redirectErrorStream(true) // Redirect error stream to input stream for easier reading
                .start()

            // Wait for the process to complete with a timeout
            val finished = process.waitFor(10, TimeUnit.SECONDS)

            if (!finished) {
                process.destroyForcibly()
                return DeviceResult.Error("ADB command timed out after 10 seconds.")
            }

            val exitCode = process.exitValue()
            val output = process.inputStream.bufferedReader().readText()

            if (exitCode != 0) {
                // ADB command failed with a non-zero exit code
                return DeviceResult.Error("ADB command failed with exit code $exitCode. Output: $output")
            }

            // Parse the output
            val devices = parseAdbOutput(output)
            DeviceResult.Success(devices)

        } catch (e: IOException) {
            // Catch IOException, which typically occurs if 'adb' is not found in PATH
            if (e.message?.contains("No such file or directory", ignoreCase = true) == true ||
                e.message?.contains("Cannot run program \"adb\"", ignoreCase = true) == true) {
                DeviceResult.Error(
                    "ADB is not installed or not found in your system's PATH. " +
                            "Please ensure Android SDK Platform-Tools are installed and 'adb' is accessible."
                )
            } else {
                DeviceResult.Error("An I/O error occurred: ${e.message}")
            }
        } catch (e: SecurityException) {
            // Catch SecurityException if there are permission issues
            DeviceResult.Error("Security error: Not permitted to execute ADB command. ${e.message}")
        } catch (e: Exception) {
            // Catch any other unexpected exceptions
            DeviceResult.Error("An unexpected error occurred: ${e.message}")
        }
    }

    /**
     * Parses the raw output from the 'adb devices -l' command into a list of [AdbDevice] objects.
     * Skips the first line which is usually "List of devices attached".
     *
     * Example output lines:
     * "emulator-5554          device product:sdk_gphone_x86 model:sdk_gphone_x86 device:generic_x86 transport_id:1"
     * "0123456789ABCDEF       device usb:1-1 product:aosp_arm64 model:Pixel_4a device:sunfish transport_id:2"
     * "ABCDEF0123456789       offline"
     */
    private fun parseAdbOutput(output: String): List<AdbDevice> {
        val devices = mutableListOf<AdbDevice>()
        output.lines().forEachIndexed { index, line ->
            // Skip the header line "List of devices attached"
            if (index == 0 && line.trim().startsWith("List of devices attached")) {
                return@forEachIndexed
            }

            val trimmedLine = line.trim()
            if (trimmedLine.isNotEmpty()) {
                // Split by whitespace. The first part is the ID, the second is the state.
                val parts = trimmedLine.split("\\s+".toRegex(), 2)
                if (parts.size >= 2) {
                    val id = parts[0]
                    val state = parts[1].split("\\s+".toRegex()).firstOrNull() ?: "unknown"
                    devices.add(AdbDevice(id, state))
                }
            }
        }
        return devices
    }
}
