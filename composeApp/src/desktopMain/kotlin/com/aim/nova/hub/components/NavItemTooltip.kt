package com.aim.nova.hub.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavItemTooltip(toolTip: String, content: @Composable () -> Unit) = TooltipArea(
    tooltip = {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(6.dp),
            tonalElevation = 4.dp,
            shadowElevation = 8.dp
        ) {
            Text(toolTip,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }
    },
    delayMillis = 300,
    tooltipPlacement = TooltipPlacement.ComponentRect(
        anchor = Alignment.CenterEnd,
        alignment = Alignment.CenterEnd,
        offset = DpOffset(x = 10.dp, y = 0.dp) // ✅ move right from cursor (or component)
    ),
    content = content
)
