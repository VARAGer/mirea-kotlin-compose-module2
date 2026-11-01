package ru.mirea.composepractices.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mirea.composepractices.R
import ru.mirea.composepractices.ui.theme.PracticeTheme

data class Contact(
    val firstName: String,
    val patronymic: String,
    val lastName: String,
    val phone: String?,
    val address: String
)

private val SampleContacts = listOf(
    Contact("Евгений", "Андреевич", "Лукашин", null, "Москва, 3-я улица Строителей"),
    Contact("Василий", "Егорович", "Кузякин", null, "Ивановская область, деревня Крутово"),
    Contact("Людмила", "Прокофьевна", "Калугина", null, "Москва, Большая Никитская")
)

@Composable
fun ContactsTask(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ContactColumn(contact = SampleContacts.first())
        ContactList(contacts = SampleContacts)
        ContactRow(contact = SampleContacts.first())
    }
}

@Composable
fun ContactColumn(contact: Contact, modifier: Modifier = Modifier) {
    Column(modifier = modifier) { ContactFields(contact = contact) }
}

@Composable
fun ContactList(contacts: List<Contact>, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        contacts.forEach { contact ->
            Column(
                modifier = Modifier.fillMaxWidth().background(Color.LightGray).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ContactFields(contact = contact, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun ContactRow(contact: Contact, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().background(Color.LightGray).padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            ContactFields(contact = contact, textAlign = TextAlign.Center)
        }
        Text(text = "★", modifier = Modifier.size(24.dp), color = Color(0xFFFF5722), fontSize = 24.sp)
    }
}

@Composable
private fun ContactFields(contact: Contact, textAlign: TextAlign = TextAlign.Start) {
    Text(text = stringResource(R.string.contact_first_name, contact.firstName), textAlign = textAlign)
    Text(text = stringResource(R.string.contact_patronymic, contact.patronymic), textAlign = textAlign)
    Text(text = stringResource(R.string.contact_last_name, contact.lastName), textAlign = textAlign)
    Text(
        text = stringResource(R.string.contact_phone, contact.phone ?: stringResource(R.string.not_specified)),
        textAlign = textAlign
    )
    Text(text = stringResource(R.string.contact_address, contact.address), textAlign = textAlign)
}

@Preview(showBackground = true)
@Composable
private fun ContactsTaskPreview() {
    PracticeTheme { ContactsTask() }
}
