package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.earth.testomania.core.helper.defaultTechQuiz
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.foundation.ProvideMergedTextStyle


@Composable
fun CreateQuizAnswerUI(possibleAnswer: Map.Entry<String, String>) {

    var isSelected by remember { mutableStateOf(false) }
    val answerState by remember { mutableStateOf(AnswerTileState.INCORRECT) }

    TestomaniaChoiceTile(
        selected = isSelected,
        onSelect = { isSelected = !isSelected },
        title = possibleAnswer.key,
        toggleColorType = answerState,
        content = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = possibleAnswer.value,
                fontWeight = FontWeight(499),
            )
        },
    )
}

@Preview
@Composable
private fun Preview() {
    CreateQuizAnswerUI(defaultTechQuiz().possibleAnswers.firstNotNullOf {
        it
    })
}

@Composable
fun TestomaniaChoiceTile(
    selected: Boolean,
    title: String,
    toggleColorType: AnswerTileState,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null,
) {
    val color by animateColorAsState(
        targetValue = when (selected) {
            true -> OrbitTheme.colors.info.normal
            false -> Color.Transparent
        }
    )
    Card(
        onClick = onSelect,
        border = BorderStroke(2.dp, color),
        modifier = modifier.semantics {
            this.selected = selected
        },
    ) {
        Column(
            Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            ProvideMergedTextStyle(value = OrbitTheme.typography.title3) {
                Text(text = title)
            }
            ChoiceTileFooter(
                toggleColorType = toggleColorType,
                content = content,
                selected = selected,
            )
        }
    }
}

@Composable
private fun ChoiceTileFooter(
    toggleColorType: AnswerTileState,
    content: @Composable (() -> Unit)?,
    selected: Boolean,
) {
    Row(
        Modifier.padding(top = 12.dp)
    ) {
        Box(
            Modifier.weight(1f),
        ) {
            ProvideMergedTextStyle(
                value = OrbitTheme.typography.title3.copy(
                    color = when (selected) {
                        true -> OrbitTheme.colors.info.normal
                        false -> Color.Unspecified
                    }
                ),
            ) {
                if (content != null) {
                    content()
                }
            }
        }

        Radio(
            selected = selected,
            onClick = null,
            toggleColorType = toggleColorType,
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(2.dp)
        )

    }
}

@Composable
private fun Radio(
    selected: Boolean,
    onClick: (() -> Unit)?,
    toggleColorType: AnswerTileState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val borderWidth by animateDpAsState(
        targetValue = if (selected) 6.dp else 2.dp,
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val borderColor by animateColorAsState(
        targetValue = when {
            !enabled || !selected -> OrbitTheme.colors.surface.disabled
            toggleColorType == AnswerTileState.CORRECT -> OrbitTheme.colors.success.normal
            toggleColorType == AnswerTileState.NEUTRAL -> OrbitTheme.colors.info.normal
            toggleColorType == AnswerTileState.INCORRECT -> OrbitTheme.colors.critical.normalAlt
            else -> OrbitTheme.colors.info.normal
        },
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val backgroundColor by animateColorAsState(
        targetValue = when {
            !enabled && !selected -> OrbitTheme.colors.surface.subtle
            else -> Color.Transparent
        },
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val selectableModifier =
        if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = RadioRippleRadius
                )
            )
        } else {
            Modifier
        }

    Canvas(
        modifier
            .then(selectableModifier)
            .requiredSize(RadioSize)
    ) {
        drawRadio(borderWidth, borderColor, backgroundColor)
    }
}

private fun DrawScope.drawRadio(borderWidth: Dp, borderColor: Color, backgroundColor: Color) {
    val borderWidthPx = borderWidth.toPx()
    drawCircle(backgroundColor, RadioRadiusSize.toPx(), style = Fill)
    drawCircle(borderColor,
        RadioRadiusSize.toPx() - borderWidthPx / 2,
        style = Stroke(borderWidthPx))
}

private fun DrawScope.drawError(strokeColor: Color, shadowColor: Color, alpha: Float) {
    if (alpha == 0f) return

    val shadowWidth = 4.dp.toPx()
    val shadowRadius = ErrorShadowRadius.toPx() - shadowWidth / 2
    drawCircle(shadowColor, shadowRadius, alpha = alpha, style = Stroke(shadowWidth))

    val strokeWidth = 2.dp.toPx()
    val strokeRadius = RadioRadiusSize.toPx() - strokeWidth / 2
    drawCircle(strokeColor, strokeRadius, alpha = alpha, style = Stroke(strokeWidth))
}

private const val RadioAnimationDuration = 100

private val RadioSize = 20.dp
private val RadioRadiusSize = RadioSize / 2
private val RadioRippleRadius = 20.dp
private val ErrorShadowSize = 24.dp
private val ErrorShadowRadius = ErrorShadowSize / 2

enum class AnswerTileState {
    NEUTRAL, INCORRECT, CORRECT;
}