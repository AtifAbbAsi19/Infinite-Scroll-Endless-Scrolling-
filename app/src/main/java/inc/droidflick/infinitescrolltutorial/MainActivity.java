package inc.droidflick.infinitescrolltutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    boolean userScrolled = false;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        prepareMovieData();

        implementScrollListener();


    }

    private void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MoviesAdapter(movieList);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }


    // Implement scroll listener
    public void implementScrollListener() {

        recyclerView
                .addOnScrollListener(new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView,
                                                     int newState) {

                        super.onScrollStateChanged(recyclerView, newState);

                        // If scroll state is touch scroll then set userScrolled
                        // true
                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                            userScrolled = true;

                        }

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx,
                                           int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        // Here get the child count, item count and visibleitems
                        // from layout manager

                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();
                        pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                        // Now check if userScrolled is true and also check if
                        // the item is end then update recycler view and set
                        // userScrolled to false
                        if (userScrolled
                                && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
                            userScrolled = false;
                            Toast.makeText(MainActivity.this, "Scrolling", Toast.LENGTH_SHORT).show();
                            prepareMovieData();
                        }
                    }
                });
    }


    private void prepareMovieData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "1");
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2");
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "3");
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "4");
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "5");
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "6");
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "7");
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "8");
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "9");
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "10");
        movieList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "11");
        movieList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "12");
        movieList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "13");
        movieList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "14");
        movieList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "15");
        movieList.add(movie);

//        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
//        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}


