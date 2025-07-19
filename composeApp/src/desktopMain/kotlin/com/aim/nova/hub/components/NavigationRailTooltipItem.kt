package com.aim.nova.hub.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

data class NavItem(val route: String, val icon: ImageVector, val label: String)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationRailTooltipItem(
    item: NavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    TooltipArea(
        tooltip = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(6.dp),
                tonalElevation = 4.dp,
                shadowElevation = 8.dp
            ) {
                Text(
                    item.label,
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
        )
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                    else Color.Transparent
                )
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = if (selected) MaterialTheme.colorScheme.primary else LocalContentColor.current
            )
        }
    }
}
