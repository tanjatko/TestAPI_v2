package megogo.responseProviderClasses;

import javax.xml.bind.annotation.XmlAttribute;

public class Channel
{
    private String id;

    @XmlAttribute
    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+"]";
    }
}
