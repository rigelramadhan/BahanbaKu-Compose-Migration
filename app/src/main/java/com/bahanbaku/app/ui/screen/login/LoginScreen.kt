package com.bahanbaku.app.ui.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bahanbaku.app.R
import com.bahanbaku.app.ui.theme.BahanbaKuTheme
import com.bahanbaku.app.ui.theme.GrayText
import com.bahanbaku.app.ui.theme.WhitePure
import com.bahanbaku.app.ui.theme.YellowPrimary

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,

    ) {

}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onLoginButtonPressed: (email: String, password: String) -> Unit,
    navigateToRegister: () -> Unit,
) {
    var emailText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var passwordText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var visiblePassword by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.login_greetings),
            color = GrayText,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.body2,
        )
        Spacer(modifier = Modifier.height(24.dp))

        BahanbaKuDefaultTextField(
            value = emailText,
            label = { Text(text = stringResource(R.string.email)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.MailOutline,
                    contentDescription = stringResource(R.string.description_email_text_field)
                )
            },
            onValueChange = { emailText = it },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))

        BahanbaKuDefaultTextField(
            value = passwordText,
            label = { Text(text = stringResource(R.string.password)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = stringResource(R.string.description_password_text_field)
                )
            },
            trailingIcon = {
                val icon =
                    if (visiblePassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

                val description =
                    if (visiblePassword)
                        stringResource(R.string.hide_password)
                    else stringResource(
                        R.string.show_password
                    )

                IconButton(onClick = {
                    visiblePassword = !visiblePassword
                }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            },
            onValueChange = { passwordText = it },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(42.dp))

        Button(
            onClick = { onLoginButtonPressed(emailText.text, passwordText.text) },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = YellowPrimary,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = stringResource(R.string.login),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.create_an_account),
            color = GrayText,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = navigateToRegister,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = WhitePure,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = stringResource(R.string.register),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun BahanbaKuDefaultTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    label: @Composable() (() -> Unit)?,
    leadingIcon: @Composable() (() -> Unit)?,
    trailingIcon: @Composable() (() -> Unit)? = {},
    onValueChange: (TextFieldValue) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = value,
        label = label,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = GrayText,
            backgroundColor = WhitePure,
        ),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    BahanbaKuTheme {
        LoginContent(onLoginButtonPressed = { _, _ ->

        }, navigateToRegister = {

        })
    }
}