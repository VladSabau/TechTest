package babylon.test

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import org.hamcrest.Matcher

/**
 * Created by Vlad Sabau on 16.03.19.
 */
class PostsKakaoScreen : Screen<PostsKakaoScreen>() {

    val noPostsTextView: KTextView =
            KTextView { withId(R.id.posts_not_available) }

    val postsRecycler: KRecyclerView = KRecyclerView({
        withId(R.id.posts_recycler_view)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val layout: KView = KView(parent) { withId(R.id.post_layout) }
        val title: KTextView = KTextView(parent) { withId(R.id.post_title) }
    }
}