package sixth.example.contentproviders;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.provider.ContactsContract;
import android.database.Cursor;
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
