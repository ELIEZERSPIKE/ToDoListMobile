package com.example.todolist_brief.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist_brief.R
import com.example.todolist_brief.ui.theme.ToDoListBriefTheme

@Composable
fun DefaultCheckBox(
    modifier: Modifier = Modifier,
    label: String = "",
    isChecked: Boolean = false,
    onValueChange: (Boolean) -> Unit = {}
) {
    val checkboxColor by animateColorAsState(
        targetValue = if (isChecked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
        label = ""
    )

    val density = LocalDensity.current
    val duration = 500
    val size = 22.dp
    val densityValue = 0.5f

    ToDoListBriefTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_8)))
                .toggleable(
                    value = isChecked,
                    role = androidx.compose.ui.semantics.Role.Checkbox,
                    onValueChange = onValueChange
                )
        ) {
            Box(
                modifier = Modifier
                    .size(size)
                    .background(
                        color = checkboxColor,
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_2))
                    )
                    .border(
                        width = dimensionResource(id = R.dimen.dp_2),
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_2))
                    )
                    .padding(dimensionResource(id = R.dimen.dp_2)),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.animation.AnimatedVisibility(
                    visible = isChecked,
                    enter = slideInHorizontally(animationSpec = tween(duration)) {
                        with(density) { (size.toPx() * -densityValue).toInt() }
                    } + expandHorizontally(
                        expandFrom = Alignment.Start,
                        animationSpec = tween(duration)
                    ),
                    exit = fadeOut()
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
            }

            Text(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.size_2)),
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = integerResource(id = R.integer.max_line_1)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_2)))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultCheckBoxPreview() {
    ToDoListBriefTheme {
        DefaultCheckBox()
        }
}
