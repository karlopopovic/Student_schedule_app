// Generated by view binder compiler. Do not edit!
package com.example.student_schedule_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.student_schedule_app.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PopUpBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView popUp;

  @NonNull
  public final RelativeLayout popUpWindow;

  private PopUpBinding(@NonNull RelativeLayout rootView, @NonNull TextView popUp,
      @NonNull RelativeLayout popUpWindow) {
    this.rootView = rootView;
    this.popUp = popUp;
    this.popUpWindow = popUpWindow;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PopUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PopUpBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.pop_up, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PopUpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.pop_up;
      TextView popUp = rootView.findViewById(id);
      if (popUp == null) {
        break missingId;
      }

      RelativeLayout popUpWindow = (RelativeLayout) rootView;

      return new PopUpBinding((RelativeLayout) rootView, popUp, popUpWindow);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
