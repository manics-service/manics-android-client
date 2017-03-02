package com.app.manics.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.manics.R;
import com.app.manics.models.AuthInfo;
import com.app.manics.networks.ManicsApi;
import com.app.manics.networks.ManicsApiClient;
import com.app.manics.tool.SessionManager;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends FragmentActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                switch (res) {
                    case LoggedOut:
                        showLogin();
                        break;
                    case LoggedIn:
                        startChartActivity();
                        break;
                    case Pending:
                        break;
                    case Unknown:
                        break;
                }

            }

            @Override
            public void onError(VKError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (VKSdk.isLoggedIn()) {
            startChartActivity();
        } else {
            showLogin();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showLogin() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .commitAllowingStateLoss();
    }

    public static class LoginFragment extends Fragment {

        private static final String[] SCOPE = new String[]{
                VKScope.MESSAGES
        };

        public LoginFragment() {
            super();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View loginView = inflater.inflate(R.layout.fragment_login, container, false);
            loginView.findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VKSdk.login(getActivity(), SCOPE);
                }
            });
            return loginView;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Log.d(TAG, "onActivityResult");
                AuthInfo authInfo = new AuthInfo(res.accessToken, "VK");

                ManicsApi manicsApi = ManicsApiClient.getClient().create(ManicsApi.class);
                Call<AuthInfo> call = manicsApi.createUser(authInfo);

                call.enqueue(new Callback<AuthInfo>() {
                    @Override
                    public void onResponse(Call<AuthInfo>call, Response<AuthInfo> response) {
                        //todo add progress
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.createSession(response.body().getSession());
                        startChartActivity();
                    }

                    @Override
                    public void onFailure(Call<AuthInfo>call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });
            }

            @Override
            public void onError(VKError error) {
                //todo user didn't pass Authorization
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void startChartActivity() {
        //todo send request to the server
        boolean analytics = false;
        if (analytics) {
            startActivity(createIntent(ContentActivity.class));
            return;
        }
        startActivity(createIntent(ChatsActivity.class));

    }

    private Intent createIntent(Class aClass) {
        Intent intent = new Intent(this, aClass);
        //todo find a better solution if possible
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }
}
