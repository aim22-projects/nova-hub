package com.aim.nova.hub.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class NavItem(val route: String, val icon: ImageVector, val label: String)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavItem(
    item: NavItem,
    size: Dp = 36.dp,
    selected: Boolean,
    onClick: () -> Unit
) = TextButton(onClick,
    shape = RoundedCornerShape(8.dp), // Rounded corners
    contentPadding = PaddingValues(8.dp), // No extra padding
    modifier = Modifier
        .size(size) // Square: equal width and height
        .background(
            if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
            else Color.Transparent
        )
        .clip(RoundedCornerShape(8.dp)),
) {
    Icon(
        imageVector = item.icon,
        contentDescription = item.label,
        tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
    )
}
