package com.codebusters.medelivery;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

public class StatusFragment extends Fragment {
    private static final String TAG = "StatusFragment";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.status_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.medical_kits);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        String[] myDataset = setupData();
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AppointmentActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private String[] setupData() {
        // Sets up data, later will get items from user's database
        String[] user_medical_items = new String[3];
        user_medical_items[0] = "Covid Testing Kit";
        user_medical_items[1] = "Blood Glucose Monitor";
        user_medical_items[2] = "Body Temperature Monitors";
        return user_medical_items;
    }

}

