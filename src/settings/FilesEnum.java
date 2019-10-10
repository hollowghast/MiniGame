package settings;

/**
 *
 * @author owner
 */
public enum FilesEnum
{
    COLORS("src/settings/colors.xml");
    
    private final String relpath;
    private FilesEnum(String filename){
        relpath = filename;
    }
    
    public String getPath(){
        return relpath;
    }
}
