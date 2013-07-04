package com.mtg.web.dto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;

import com.mtg.commons.models.collections.Wanted;
import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.interactive.Post;

public class DtoMaker {

    private static DtoMaker instance = new DtoMaker();
    
    /**
     * Just a chainable string,object hashmap
     */
    public class ChainableMap extends HashMap<String, Object> {
        private static final long serialVersionUID = 1L;
        @Override
        public ChainableMap put(String key, Object val) {
            super.put(key, val);
            return this;
        }
    }
    
    private static ChainableMap map() {
        return instance.new ChainableMap();
    }
    
    public static Map<String,Object> transform(Wanted wanted) {
        Validate.notNull(wanted);
        return map()
                .put("count", wanted.getCount())
                .put("note", wanted.getNote());    
    }

	public static Map<String, Object> transform(Post post) {
		Validate.notNull(post);
		return map()
				.put("title", post.getTitle())
				.put("text", post.getText())
				.put("postDate", post.getPostdate());
	}

    public static Map<String, Object> transform(Comment comment) {
        Validate.notNull(comment);
        return map()
                .put("id", comment.getId());
    }
    
}
