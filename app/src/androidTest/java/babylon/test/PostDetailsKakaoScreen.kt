package babylon.test

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView

/**
 * Created by Vlad Sabau on 18.03.19.
 */
class PostDetailsKakaoScreen: Screen<PostDetailsKakaoScreen>() {

    val titleTextView: KTextView =
            KTextView { withId(R.id.post_title) }

    val bodyTextView: KTextView =
            KTextView { withId(R.id.post_body) }

    val usernameTextView: KTextView =
            KTextView { withId(R.id.username) }

    val nbCommentsTextView: KTextView =
            KTextView { withId(R.id.number_comments) }

}