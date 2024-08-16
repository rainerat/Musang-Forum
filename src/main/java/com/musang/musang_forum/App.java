package com.musang.musang_forum;

import com.musang.musang_forum.client.Client;
import com.musang.musang_forum.model.CurrentUser;
import com.musang.musang_forum.model.User;
import com.musang.musang_forum.service.EncryptionService;
import com.musang.musang_forum.util.ClientManager;

public class App {
    public static final String SIGNUP_PATH = "view/general/SignUp.fxml";
    public static final String SIGNIN_PATH = "view/general/SignIn.fxml";
    public static final String POPUP_PATH = "view/general/PopUp.fxml";
    public static final String FORUM_PATH = "view/main/Forum.fxml";
    public static final String HOME_PATH = "view/main/Home.fxml";
    public static final String START_DISCUSSION_PATH = "view/main/nested/StartDiscussion.fxml";
    public static final String DISCUSSION_PATH = "view/main/nested/AllDiscussions.fxml";
    public static final String FOLLOWING_PATH = "view/main/nested/Following.fxml";
    public static final String TAGS_PATH = "view/main/nested/Tags.fxml";
    public static final String SETTINGS_PATH = "view/settings/Settings.fxml";
    public static final String MYACCOUNT_PATH = "view/settings/nested/MyAccount.fxml";
    public static final String CHANGEPW_PATH = "view/settings/nested/UpdatePassword.fxml";
    public static final String PERSONALIZE_PATH = "view/settings/nested/Personalize.fxml";
    public static final String INFORMATION_PATH = "view/settings/nested/Information.fxml";
    public static final String MESSAGE_BUBBLE_PATH = "component/MessageBubble.fxml";
    public static final String FORUM_BUBBLE_PATH = "component/ForumBubble.fxml";

    private final EncryptionService encryptionService;

    public App() {
        encryptionService = new EncryptionService();
    }

    public EncryptionService getEncryptionService() {
        return encryptionService;
    }

    public User getCurrentUser() {
        return CurrentUser.get();
    }

    public Client getClient() {
        return ClientManager.getClient();
    }
}
