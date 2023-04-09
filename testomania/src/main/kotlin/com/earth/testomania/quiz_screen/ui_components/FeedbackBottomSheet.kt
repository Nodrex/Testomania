package com.earth.testomania.quiz_screen.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earth.testomania.R
import com.earth.testomania.quiz_categories.viewmodel.DestinationViewModel
import com.earth.testomania.ui.theme.Gray
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.SurfaceCard
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun FeedbackBottomSheet(
    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    viewModel: DestinationViewModel,
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
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body1,
            shape = RoundedCornerShape(6.dp),
            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Send),
            keyboardActions = KeyboardActions(onSend = {
                sendFeedbackAndCloseBottomSheet(scope, modalBottomSheetState, text, viewModel, keyboardController)
                text = ""
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
                sendFeedbackAndCloseBottomSheet(
                    scope,
                    modalBottomSheetState,
                    text,
                    viewModel,
                    keyboardController
                )
                text = ""
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

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
private fun sendFeedbackAndCloseBottomSheet(
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    text: String,
    viewModel: DestinationViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    viewModel.sendFeedback(text)
    keyboardController?.hide()
    scope.launch {
        modalBottomSheetState.hide()
    }
}


