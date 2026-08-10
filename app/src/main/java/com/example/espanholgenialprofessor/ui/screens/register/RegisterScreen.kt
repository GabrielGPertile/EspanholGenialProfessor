package com.example.espanholgenialprofessor.ui.screens.register

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.espanholgenialprofessor.R
import com.example.espanholgenialprofessor.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val snackbarHostState = remember {
        androidx.compose.material3.SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { padding ->

        val passwordVisible = remember {
            mutableStateOf(false)
        }

        val confirmPasswordVisible = remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.logo),
                modifier = Modifier.size(200.dp),
                contentDescription = "Logo do aplicativo"
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Criar conta",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Crie sua conta para começar a utilizar o Espanhol Genial.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            OutlinedTextField(
                value = viewModel.uiState.email,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
                label = {
                    Text("E-mail")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Ícone de e-mail"
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                value = viewModel.uiState.password,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
                label = {
                    Text("Senha")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Ícone de senha"
                    )
                },
                visualTransformation =
                    if (passwordVisible.value) {
                        androidx.compose.ui.text.input.VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisible.value =
                                !passwordVisible.value
                        }
                    ) {
                        Icon(
                            imageVector =
                                if (passwordVisible.value)
                                    Icons.Default.Visibility
                                else
                                    Icons.Default.VisibilityOff,
                            contentDescription =
                                if (passwordVisible.value)
                                    "Ocultar senha"
                                else
                                    "Mostrar senha"
                        )
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                value = viewModel.uiState.confirmPassword,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.onConfirmPasswordChange(it)
                },
                label = {
                    Text("Confirmar senha")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Ícone de senha"
                    )
                },
                visualTransformation =
                    if (confirmPasswordVisible.value) {
                        androidx.compose.ui.text.input.VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            confirmPasswordVisible.value =
                                !confirmPasswordVisible.value
                        }
                    ) {
                        Icon(
                            imageVector =
                                if (confirmPasswordVisible.value)
                                    Icons.Default.Visibility
                                else
                                    Icons.Default.VisibilityOff,
                            contentDescription =
                                if (confirmPasswordVisible.value)
                                    "Ocultar senha"
                                else
                                    "Mostrar senha"
                        )
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "O cadastro realizado por esta tela cria uma conta de aluno.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            viewModel.uiState.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.register(
                        onSuccess = {
                            navController.navigate(Routes.HOME) {
                                popUpTo(Routes.LOGIN) {
                                    inclusive = true
                                }

                                launchSingleTop = true
                            }
                        }
                    )
                },
                enabled =
                    !viewModel.uiState.isLoading &&
                            viewModel.uiState.email.isNotBlank() &&
                            viewModel.uiState.password.isNotBlank() &&
                            viewModel.uiState.confirmPassword.isNotBlank()
            ) {
                Text(
                    if (viewModel.uiState.isLoading)
                        "Criando conta..."
                    else
                        "Criar conta"
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Para professores",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "O cadastro de professor é realizado mediante solicitação e análise das informações fornecidas.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Informe corretamente seus dados, sua instituição de ensino e como pretende utilizar o Espanhol Genial. Essas informações serão utilizadas para analisar sua solicitação.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Após a aprovação, sua conta de professor será criada e você receberá as orientações para acessar o aplicativo.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    val subject = Uri.encode(
                        "Solicitação de cadastro como professor"
                    )

                    val body = Uri.encode(
                        """
            Olá,

            Gostaria de solicitar um cadastro como professor no Espanhol Genial.

            Dados para análise:

            Nome completo:
            E-mail que será utilizado no aplicativo:
            Telefone:
            Instituição de ensino:
            CNPJ da instituição:
            Cidade/Estado:
            Cargo/Função:
            Disciplinas que leciona:
            Faixa etária/série dos alunos:

            Justificativa para utilização do aplicativo:

            Como pretendo utilizar o aplicativo:

            Declaro que as informações fornecidas são verdadeiras e que utilizarei o aplicativo de acordo com sua finalidade educacional.

            Atenciosamente,
            """.trimIndent()
                    )

                    val intent = Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse(
                            "mailto:espanholgenialensino@gmail.com" +
                                    "?subject=$subject" +
                                    "&body=$body"
                        )
                    )

                    try {
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Não encontramos um aplicativo de e-mail neste dispositivo. " +
                                        "Você pode enviar a solicitação pelo computador para " +
                                        "espanholgenialensino@gmail.com."
                            )
                        }
                    }
                }
            ) {
                Text("Solicitar cadastro como professor")
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            TextButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Voltar ao login")
            }
        }
    }
}