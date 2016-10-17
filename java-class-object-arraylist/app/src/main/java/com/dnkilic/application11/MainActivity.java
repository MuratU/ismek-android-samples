package com.dnkilic.application11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etBookName, etAuthorName, etAuthorSurname;
    TextView tvBooks;
    ArrayList<Book> mBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Author author1 = new Author("Fernando", "Pessoa");
        Book book1 = new Book("Anarşist Banker", author1);

        Author author2 = new Author("Mümin", "Sekman");
        Book book2 = new Book("Her Şey Seninle Başlar", author2);

        Author author3 = new Author();
        Book book3 = new Book("Keloğlan", author3);*/


        etBookName = (EditText) findViewById(R.id.etBookName);
        etAuthorName = (EditText) findViewById(R.id.etAuthorName);
        etAuthorSurname = (EditText) findViewById(R.id.etAuthorSurname);

        tvBooks = (TextView) findViewById(R.id.tvBooks);

        mBooks = new ArrayList<>();
    }

    public void saveBook(View view) {
        String bookName = etBookName.getText().toString();
        String authorName = etAuthorName.getText().toString();
        String authorSurname = etAuthorSurname.getText().toString();

        Author author = new Author(authorName, authorSurname);
        Book book = new Book(bookName, author);

        Toast.makeText(getApplicationContext(), "Kitap Eklendi", Toast.LENGTH_SHORT).show();

        mBooks.add(book);
    }

    public void listBooks(View view)
    {
        String books = "";

        for (Book book : mBooks)
        {
            books = books + book.mBookName + " " + book.mAuthor.mName + " " + book.mAuthor.mSurname + "\n";
        }

        tvBooks.setText(books);
    }

    public void deleteBook(View view)
    {
        String bookToRemove = etBookName.getText().toString();

        for(int i=0 ; i<mBooks.size() ; i++)
        {
            if(bookToRemove.equals(mBooks.get(i).mBookName))
            {
                mBooks.remove(i);
            }
        }
    }

}
