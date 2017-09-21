package ru.idea.possiblebug;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Created 20/09/17 16:06
 *
 * @author Vladimir Bogodukhov
 **/
public class TestWithResource {


    @Test
    public void testResources() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(loadResourceByRelativePath(TestWithResource.class, "res/file.txt")))) {
            int sum = reader.lines().mapToInt(Integer::valueOf).sum();
            Assert.assertEquals(55, sum);
        }
    }

    private static InputStream loadResourceByRelativePath(Class startClass, String relativeResourcePath) {
        Objects.requireNonNull(startClass);
        Objects.requireNonNull(relativeResourcePath);

        InputStream resource = startClass.getResourceAsStream(relativeResourcePath);
        if (resource == null) {
            throw new IllegalArgumentException("resource with path " + relativeResourcePath +
                    " is not found. search from class " + startClass);
        }
        return resource;
    }


}
