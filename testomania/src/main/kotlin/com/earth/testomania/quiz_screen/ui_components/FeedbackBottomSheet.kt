package com.earth.testomania.quiz_screen.ui_components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.testomania.R
import com.earth.testomania.ui.theme.Gray
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.SurfaceCard
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedbackBottomSheet(
    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
) {
    Column(
        Modifier
            .padding(all = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            fontSize = 16.sp,
            text = stringResource(id = R.string.feedback_prompt)
        )
        Spacer(modifier = Modifier.height(20.dp))

        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body1,
            shape = RoundedCornerShape(6.dp),
            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Send),
            keyboardActions = KeyboardActions(onDone = {
                sendFeedbackAndCloseBottomSheet(scope, modalBottomSheetState)
            }),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isSystemInDarkTheme()) Gray else Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = OrbitTheme.colors.primary.strong,
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        SurfaceCard(
            modifier = Modifier
                .size(50.dp)
                .background(color = OrbitTheme.colors.primary.strong, shape = CircleShape),
            shape = CircleShape,
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            enabled = true,
            onClick = {
                sendFeedbackAndCloseBottomSheet(scope, modalBottomSheetState)
            }) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                kiwi.orbit.compose.ui.controls.Icon(
                    painter = painterResource(R.drawable.ic_send),
                    contentDescription = "Send feedback",
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
private fun sendFeedbackAndCloseBottomSheet(
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState
) {
    //TODO send feedback with user text and attache id or quiz
    scope.launch {
        modalBottomSheetState.hide()
    }
}


