package megogo.responseProviderClasses;


public class Category
{
    private String content;

    private String lang;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getLang ()
    {
        return lang;
    }

    public void setLang (String lang)
    {
        this.lang = lang;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", lang = "+lang+"]";
    }
}