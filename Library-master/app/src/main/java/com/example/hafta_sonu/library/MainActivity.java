package com.example.hafta_sonu.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Book> listOfBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfBooks = new ArrayList<>();
    }

    public void saveBook(View v)
    {
        EditText etBookName = (EditText) findViewById(R.id.etBookName);
        EditText etWriterName = (EditText) findViewById(R.id.etWriterName);
        EditText etWriterSurname = (EditText) findViewById(R.id.etWriterSurname);

        String bookName = etBookName.getText().toString();
        String writerName = etWriterName.getText().toString();
        String writerSurname = etWriterSurname.getText().toString();

        if(bookName.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Kitap ismi boş olamaz", Toast.LENGTH_LONG).show();
        }
        else
        {
            if(writerSurname.isEmpty() && writerName.isEmpty())
            {
                Book book = new Book(bookName);
                listOfBooks.add(book);
                list();
                Log.i("Writer" , "Kullanıcı yazar ismi ve soyismini girmedi");
                Log.i("Book" , "Kitabın ismi ise " + bookName);
            }
            else if(writerSurname.isEmpty() && !writerName.isEmpty())
            {
                Writer writer = new Writer(writerName);
                Book book = new Book(writer, bookName);
                listOfBooks.add(book);
                list();
                Log.i("Writer" , "Kullanıcı yazar ismi girdi : " + writerName);
                Log.i("Book" , "Kitabın ismi ise " + bookName);
            }
            else if(!writerSurname.isEmpty() && writerName.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Yazarın ismi yok soyismi var. Bu nasıl iş?", Toast.LENGTH_LONG).show();
            }
            else
            {
                Writer writer = new Writer(writerName, writerSurname);
                Book book = new Book(writer, bookName);
                listOfBooks.add(book);
                list();
                Log.i("Writer" , "Kullanıcı yazar ismi girdi : " + writerName + " ve yazar soyismi girdi : " + writerSurname);
                Log.i("Book" , "Kitabın ismi ise " + bookName);
            }
            Toast.makeText(getApplicationContext(), "Kitap eklendi : " + bookName, Toast.LENGTH_LONG).show();

            etBookName.setText("");
            etWriterName.setText("");
            etWriterSurname.setText("");
        }
    }

    public void deleteBooks(View v)
    {
        listOfBooks.clear();

        TextView tvList = (TextView) findViewById(R.id.tvBookList);
        tvList.setText("");
    }

    public void list()
    {
        if(listOfBooks.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Listeniz Henüz Boş", Toast.LENGTH_LONG).show();
        }
        else
        {
            TextView tvList = (TextView) findViewById(R.id.tvBookList);
            String listAsString = "";

            for(Book book : listOfBooks)
            {
                String bookName = book.bookName;
                String writerName = book.writer.name;
                String writerSurname = book.writer.surname;

                if(writerSurname != null)
                {
                    listAsString = listAsString + "BookName : " + bookName + "\n"
                            + "Writer : " + writerName + " " + writerSurname + "\n\n";
                }
                else
                {
                    listAsString = listAsString + "BookName : " + bookName + "\n"
                            + "Writer : " + writerName + "\n\n";
                }
            }
            tvList.setText(listAsString);
        }
    }

    public void deleteBook(View v)
    {
        EditText etBookName = (EditText) findViewById(R.id.etBookName);
        String bookName = etBookName.getText().toString();

        if(listOfBooks.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Boş bir listede silme işlemi yapmayı denediniz.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(bookName.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Kitap silme işlemi yapmak için kitap adı girmeniz gerekiyor.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ArrayList<Book> refreshedListOfBooks = new ArrayList<>();

                for(Book book : listOfBooks)
                {
                    if(!book.bookName.equalsIgnoreCase(bookName))
                    {
                        refreshedListOfBooks.add(book);
                    }
                }

                listOfBooks = refreshedListOfBooks;

                list();
            }
        }
    }
}
