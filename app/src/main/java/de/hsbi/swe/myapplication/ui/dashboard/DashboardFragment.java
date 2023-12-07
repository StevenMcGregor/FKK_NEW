package de.hsbi.swe.myapplication.ui.dashboard;
import android.app.AlertDialog;
import android.content.DialogInterface;
import de.hsbi.swe.myapplication.R;
import android.widget.Button;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import de.hsbi.swe.myapplication.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Hier ändern: 'root.findViewById' statt 'findViewById'
        Button myButton = root.findViewById(R.id.MyButton);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Die Funktion, die ausgeführt wird, wenn der Button geklickt wird
                Press();
            }
        });

        return root;
    }

    public void Press() {
        // Hier können Sie die Funktion für den Button-Click auch aufrufen, falls gewünscht
        createNewButton("new Button");
        showInputDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void createNewButton(String buttonText) {
        // Einen neuen Button erstellen
        Button newButton = new Button(requireContext());
        newButton.setText(buttonText);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Die Funktion, die ausgeführt wird, wenn der Button geklickt wird
                removeButton(newButton);
            }
        });

        // Den neuen Button dem Layout hinzufügen
        LinearLayout linearLayout = binding.MyLayout; // Annahme: ID des Containers in XML ist "linearLayout"
        linearLayout.addView(newButton);
    }
    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Benutzereingabe");

        // Erstellen Sie ein neues EditText-Objekt
        final EditText input = new EditText(requireContext());

        // Fügen Sie das EditText-Objekt zum Dialog hinzu
        builder.setView(input);

        // Fügen Sie die Aktionen für die Schaltflächen hinzu
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInput = input.getText().toString();
                createNewButton(userInput);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    private void removeButton(Button button) {

        LinearLayout linearLayout = binding.MyLayout;
        if (button != null) {
            linearLayout.removeView(button);
        }
    }
}


