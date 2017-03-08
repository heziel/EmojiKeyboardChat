package keyboard.emoji_library.Helper;

import android.content.Context;
import android.widget.GridView;

import keyboard.emoji_library.Emoji.Emojicon;
import keyboard.emoji_library.R;

/**
 * EmojiconRecentsGridView
 */

public class EmojiconRecentsGridView extends EmojiconGridView implements EmojiconRecents {
    EmojiAdapter mAdapter;
    private boolean mUseSystemDefault = false;


    public EmojiconRecentsGridView(Context context, Emojicon[] emojicons,
                                   EmojiconRecents recents, EmojiconsPopup emojiconsPopup, boolean useSystemDefault) {
        super(context, emojicons, recents, emojiconsPopup, useSystemDefault);
        this.mUseSystemDefault = useSystemDefault;
        EmojiconRecentsManager recents1 = EmojiconRecentsManager
                .getInstance(rootView.getContext());
        mAdapter = new EmojiAdapter(rootView.getContext(), recents1, mUseSystemDefault);
        mAdapter.setEmojiClickListener(new OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                if (mEmojiconPopup.onEmojiconClickedListener != null) {
                    mEmojiconPopup.onEmojiconClickedListener.onEmojiconClicked(emojicon);
                }
            }
        });
        GridView gridView = (GridView) rootView.findViewById(R.id.Emoji_GridView);
        gridView.setAdapter(mAdapter);
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addRecentEmoji(Context context, Emojicon emojicon) {
        EmojiconRecentsManager recents = EmojiconRecentsManager
                .getInstance(context);
        recents.push(emojicon);

        // notify dataset changed
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

}