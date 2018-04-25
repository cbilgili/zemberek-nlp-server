package utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ZemberekExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        if (f.getName().equals("suffixData") || f.getName().equals("specialRootSuffix") || f.getName().equals("referenceItem")) {
            return true;
        }
        return false;
    }

}
