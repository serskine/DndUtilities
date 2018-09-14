package com.soupthatisthick.encounterbuilder.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.view.cell.other.HtmlViewCell;
import com.soupthatisthick.util.model.Histogram;

import soupthatisthick.encounterapp.R;

public class HtmlActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    EditText editText;
    ImageButton clearButton;
    ImageButton submitButton;

    HtmlViewCell htmlCell;

    private final Histogram<String> words = new Histogram<>();

    private static final String TABLE_HTML = "<table>\n"
            + "  <tr><th>header 1</th><th>header 2</th><th>header 3</th>\n"
            + "  <tr><td>row 1.1</th><th>row 1.2</th><th>row 1.3</th>\n"
            + "  <tr><td>row 2.1</td><td>row 2.2</td><td>row 2.3</td>\n"
            + "  <tr><td>row 3.1</td><td>row 3.2</td><td>row 3.3</td>\n"
            + "  <tr><td>row 4.1</td><td>row 4.2</td><td>row 4.3</td>\n"
            + "</table>";
    private static final String LIST_HTML = "<ul>\n"
            + "  <li>List item 1</li>\n"
            + "  <li>List item 2</li>\n"
            + "  <li>List item 3</li>\n"
            + "  <li>List item 4</li>\n"
            + "</ul>";
    private static final String INLINE_HTML = "<b>The</b> quick brown <i>fox</i> jumped over the lazy dog.";
    private static final String INLINE_BOLD = "<b>The quick brown fox jumped over the lazy dog.</b>";
    private static final String INLINE_ITALIC = "<b>The quick brown fox jumped over the lazy dog.</b>";
    private static final String INLINE_LONG = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).";
    private static final String PARAGRAPH_HTML = "<p>" + INLINE_LONG + INLINE_BOLD + INLINE_ITALIC + "</p>";

    private static final String MIXED_HTML = PARAGRAPH_HTML + LIST_HTML + TABLE_HTML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        editText = findViewById(R.id.theEditText);
        linearLayout = findViewById(R.id.theRoot);
        clearButton = findViewById(R.id.theClearButton);
        submitButton = findViewById(R.id.theSubmitButton);
        initWordHistogram();

        try {
            htmlCell = new HtmlViewCell(getLayoutInflater(), null, linearLayout);
            htmlCell.updateUi(MIXED_HTML);
            linearLayout.addView(htmlCell.getView());
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }
    }

    private void initWordHistogram() {
        words.clear();
        String input = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?";
        String tokens[] = input.split(" ");
        for(String token : tokens) {
            words.increment(token);
        }
    }

    public void onClickClearEditTextButton(View view) {
        editText.setText("");
    }

    public void onClickSubmitButton(View view) {
        htmlCell.updateUi(editText.getText().toString());
    }

    public void onClickTextButton(View view) {
        int numWords = 1 + (int) Math.random() * 20;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<numWords; i++) {
            final String word = words.random();
            if (word != null) {
                sb.append(" ").append(words.random());
            }
        }
        appendHtml(sb.toString());
    }

    public void onClickParagraphButton(View view) {
        appendHtml(PARAGRAPH_HTML);
    }

    public void onClickListButton(View view) {
        appendHtml(LIST_HTML);
    }

    public void onClickTableButton(View view) {
        appendHtml(TABLE_HTML);
    }

    private void appendHtml(String html) {
        editText.setText(Text.toString(editText.getText().toString()) + Text.toString(html));
    }

}
