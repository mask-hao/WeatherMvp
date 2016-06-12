package com.zhanghao.hefenweathermvp.adapter;

import android.content.Context;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.text.TextUtils;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by 张浩 on 2016/5/29.
 */
public class SearchSuggestionsAdapter extends SimpleCursorAdapter {

    private static final String[] mFields  = { "_id", "result" };
    private static final String[] mVisible = { "result" };
    private static final int[]    mViewIds = { android.R.id.text1 };

//    public SearchSuggestionsAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
//        super(context, layout, c, from, to, flags);
//    }

    public SearchSuggestionsAdapter(Context context)
    {
        super(context, android.R.layout.simple_list_item_1, null, mVisible, mViewIds, 0);
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint)
    {
        return new SuggestionsCursor(constraint);
    }

    private static class SuggestionsCursor extends AbstractCursor
    {
        private ArrayList<String> mResults;

        public SuggestionsCursor(CharSequence constraint)
        {
            final int count = 100;
            mResults = new ArrayList<String>(count);
            for(int i = 0; i < count; i++){
                mResults.add("Result " + (i + 1));
            }
            if(!TextUtils.isEmpty(constraint)){
                String constraintString = constraint.toString().toLowerCase(Locale.ROOT);
                Iterator<String> iter = mResults.iterator();
                while(iter.hasNext()){
                    if(!iter.next().toLowerCase(Locale.ROOT).startsWith(constraintString))
                    {
                        iter.remove();
                    }
                }
            }
        }

        @Override
        public int getCount()
        {
            return mResults.size();
        }

        @Override
        public String[] getColumnNames()
        {
            return mFields;
        }

        @Override
        public long getLong(int column)
        {
            if(column == 0){
                return mPos;
            }
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public String getString(int column)
        {
            if(column == 1){
                return mResults.get(mPos);
            }
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public short getShort(int column)
        {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public int getInt(int column)
        {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public float getFloat(int column)
        {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public double getDouble(int column)
        {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public boolean isNull(int column)
        {
            return false;
        }
    }
}
