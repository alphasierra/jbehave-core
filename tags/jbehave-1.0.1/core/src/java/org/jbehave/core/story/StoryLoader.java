package org.jbehave.core.story;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;

import org.jbehave.core.story.codegen.domain.StoryDetails;
import org.jbehave.core.story.codegen.parser.StoryParser;
import org.jbehave.core.story.domain.Story;

/**
 * StoryLoader parses story details from a resource in the classpath and build a Story via the StoryBuilder.
 * 
 * @author Mauro Talevi
 * @see StoryBuilder
 */
public class StoryLoader {

    private ClassLoader classLoader;
    private StoryParser storyParser;

    public StoryLoader(StoryParser storyParser, ClassLoader classLoader) {
        this.classLoader = classLoader;
        this.storyParser = storyParser;
    }

    public Story loadStory(String storyPath, String storyPackage) throws MalformedURLException {
        StoryDetails storyDetails = storyParser.parseStory(getReader(storyPath, classLoader));
        return new StoryBuilder(storyDetails, storyPackage).story();
    }

    public Story loadStory(String storyClassName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Story) classLoader.loadClass(storyClassName).newInstance();        
    }
    
    protected Reader getReader(String resource, ClassLoader classLoader) {
        InputStream is = classLoader.getResourceAsStream(resource);
        if ( is == null ){
            throw new NoSuchElementException("Resource "+resource+" not found in ClassLoader "+classLoader.getClass());
        }
        return new InputStreamReader(is);
    }
}