package net.backport.material_dialogs_sample_java;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.customview.DialogCustomViewExtKt;
import com.afollestad.materialdialogs.input.DialogInputExtKt;
import com.afollestad.materialdialogs.list.DialogListExtKt;
import com.afollestad.materialdialogs.list.DialogMultiChoiceExtKt;
import com.afollestad.materialdialogs.list.DialogSingleChoiceExtKt;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button_basic).setOnClickListener(v -> {
			showBasicDialog();
		});

		findViewById(R.id.button_basic_res).setOnClickListener(v -> {
			showBasicResDialog();
		});

		findViewById(R.id.button_plain_list).setOnClickListener(v -> {
			showPlainListDialog();
		});

		findViewById(R.id.button_single_choice_list).setOnClickListener(v -> {
			showSingleChoiceListDialog();
		});

		findViewById(R.id.button_multi_choice_list).setOnClickListener(v -> {
			showMultiChoiceListDialog();
		});

		findViewById(R.id.button_input).setOnClickListener(v -> {
			showInputDialog();
		});

		findViewById(R.id.button_custom_view).setOnClickListener(v -> {
			showCustomViewDialog();
		});
	}

	private void showBasicDialog() {

		MaterialDialog dialog = new MaterialDialog(this, MaterialDialog.getDEFAULT_BEHAVIOR());
		dialog.title(null, "Basic dialog");
		dialog.message(null, "Dialog message.", null);
		dialog.positiveButton(null, "OK", materialDialog -> {
			Toast.makeText(MainActivity.this, "onPositive", Toast.LENGTH_SHORT).show();
			return null;
		});
		dialog.negativeButton(null, "Cancel", null);
		dialog.show();
	}

	private void showBasicResDialog() {

		MaterialDialog dialog = new MaterialDialog(this, MaterialDialog.getDEFAULT_BEHAVIOR());
		dialog.title(R.string.simple_dialog_title, null);
		dialog.message(R.string.simple_dialog_message, null, null);
		dialog.icon(R.mipmap.ic_launcher, null);
		dialog.positiveButton(R.string.simple_dialog_positive, null, null);
		dialog.negativeButton(R.string.simple_dialog_negative, null, null);
		dialog.cancelable(false);
		dialog.show();
	}

	@SuppressLint("CheckResult")
	private void showPlainListDialog() {

		String[] args = {"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow", "Nougat", "Oreo", "Pie"};
		List<String> list = Arrays.asList(args);

		MaterialDialog dialog = new MaterialDialog(this, MaterialDialog.getDEFAULT_BEHAVIOR());
		dialog.title(null, "List dialog");
		DialogListExtKt.listItems(dialog, null, list, null, false, (materialDialog, integer, s) -> {
			String str = s + " (index: " + integer + ")";
			Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
			dialog.dismiss();
			return null;
		});
		dialog.show();
	}

	@SuppressLint("CheckResult")
	private void showSingleChoiceListDialog() {

		String[] args = {"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow", "Nougat", "Oreo", "Pie"};
		List<String> list = Arrays.asList(args);

		MaterialDialog dialog = new MaterialDialog(this, MaterialDialog.getDEFAULT_BEHAVIOR());
		dialog.title(null, "List dialog");
		DialogSingleChoiceExtKt.listItemsSingleChoice(dialog, null, list, null, 0, true, (materialDialog, integer, s) -> {
			String str = s + " (index: " + integer + ")";
			Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
			return null;
		});
		dialog.positiveButton(null, "Choose", null);
		dialog.show();
	}

	@SuppressLint("CheckResult")
	private void showMultiChoiceListDialog() {

		String[] args = {"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow", "Nougat", "Oreo", "Pie"};
		List<String> list = Arrays.asList(args);

		int[] selected = new int[]{0, 1};

		MaterialDialog dialog = new MaterialDialog(this, MaterialDialog.getDEFAULT_BEHAVIOR());
		dialog.title(null, "List dialog");
		DialogMultiChoiceExtKt.listItemsMultiChoice(dialog, null, list, null, selected, true, false, (materialDialog, ints, strings) -> {

			String str = "select " + ints.length + " items.";
			Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
			return null;
		});
		dialog.positiveButton(null, "Choose", null);
		dialog.show();
	}

	@SuppressLint("CheckResult")
	private void showInputDialog() {

		MaterialDialog dialog = new MaterialDialog(this, MaterialDialog.getDEFAULT_BEHAVIOR());
		dialog.title(null, "Input dialog");
		DialogInputExtKt.input(dialog, "Hint text.", null, null, null, InputType.TYPE_CLASS_TEXT, 100, false, false, null);
		dialog.positiveButton(null, "OK", null);
		dialog.negativeButton(null, "Cancel", null);
		dialog.show();
	}

	private void showCustomViewDialog() {

		CustomView customView = new CustomView(this);

		MaterialDialog dialog = new MaterialDialog(this, MaterialDialog.getDEFAULT_BEHAVIOR());
		DialogCustomViewExtKt.customView(dialog, null, customView, false, false, true, true);
		dialog.positiveButton(null, "Close", null);
		dialog.show();
	}
}
