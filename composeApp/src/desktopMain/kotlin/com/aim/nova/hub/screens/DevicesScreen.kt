package com.aim.nova.hub.screens


import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.navigation.NavHostController
import com.aim.nova.hub.services.DesktopDeviceService
import com.aim.nova.hub.services.DeviceResult
import com.aim.nova.hub.services.IDeviceService
import kotlinx.coroutines.launch

@Composable
fun DevicesScreen(navController: NavHostController)
{
    val deviceService: IDeviceService = remember { DesktopDeviceService() }
    var deviceResult: DeviceResult by remember { mutableStateOf(DeviceResult.Success(emptyList())) }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    /**
     * Function to fetch ADB devices.
     * Sets loading state, calls the service, and updates the result.
     */
    fun fetchDevices() {
        coroutineScope.launch {
            isLoading = true
            deviceResult = deviceService.getAdbDevices()
            isLoading = false
        }
    }

    // Fetch devices when the component first mounts
    LaunchedEffect(Unit) {
        fetchDevices()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ListItem(
            headlineContent = {
                Text ("Adb Devices")
            },
            trailingContent = {
//                if (isLoading) CircularProgressIndicator(modifier = Modifier.padding(16.dp))
//                else
                IconButton({ fetchDevices() }){
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh Devices",
                    )
                }
            },
            modifier = Modifier
                .defaultMinSize(minHeight = 40.dp)
                .clip(RoundedCornerShape(8.dp)) // compact height
                //.padding(horizontal = 8.dp)
        )
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else {
            when (val result = deviceResult) {
                is DeviceResult.Success -> {
                    if (result.devices.isEmpty()) {
                        Text("No ADB devices found.", style = MaterialTheme.typography.bodyLarge) // Updated typography
                    } else {
                        Text("Connected ADB Devices:", style = MaterialTheme.typography.headlineSmall) // Updated typography
                        Spacer(Modifier.height(8.dp))
                        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
                            items(result.devices) { device ->
//val parts = it.split("\t")
//    val name = if (parts[0].startsWith("emulator")) "Android Emulator" else "Android Device"
//    val id = parts[0]
                                DeviceTile(device.id, device.state)
                            }
                        }
                    }
                }
                is DeviceResult.Error -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Error: ${result.message}",
                            color = MaterialTheme.colorScheme.error, // Updated color reference
                            style = MaterialTheme.typography.bodySmall, // Updated typography
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "Make sure 'adb' is installed and its path is added to your system's environment variables.",
                            style = MaterialTheme.typography.bodySmall, // Updated typography (caption is now bodySmall)
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun DeviceTile(name: String, id: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFFf9fafb), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFeaedf1), shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PhoneAndroid,
                    contentDescription = null,
                    tint = Color(0xFF101518)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(name, color = Color(0xFF101518), fontWeight = FontWeight.Medium)
                Text(id, color = Color(0xFF5c748a), fontSize = 13.sp)
            }
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFF101518)
        )
    }
}
