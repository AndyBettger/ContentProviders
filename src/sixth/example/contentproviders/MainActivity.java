package sixth.example.contentproviders;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button queryButton = (Button)findViewById(R.id.button1);
		queryButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				queryContact ();
			}
		});
		Button addButton = (Button)findViewById(R.id.button2);
		addButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				addContact("Steve Wozniak");
			}
		});
	}

	private void addContact(String newName) {
		ContentValues myContact = new ContentValues();
		myContact.put(RawContacts.ACCOUNT_NAME, newName);
		myContact.put(RawContacts.ACCOUNT_TYPE, newName);
		Uri addUri = getContentResolver().insert(RawContacts.CONTENT_URI, myContact);
		long rawContactId = ContentUris.parseId(addUri);
		myContact.clear();
		myContact.put(Data.RAW_CONTACT_ID, rawContactId);
		myContact.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
		myContact.put(StructuredName.DISPLAY_NAME, newName);
		getContentResolver().insert(Data.CONTENT_URI, myContact);
		Toast.makeText(this, "New Contact: " +newName, Toast.LENGTH_SHORT).show();
	}

	private void queryContact() {
		Cursor nameCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		while (nameCursor.moveToNext()) {
			String contactName = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
			Toast.makeText(this, contactName, Toast.LENGTH_SHORT).show();
		}
		nameCursor.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
