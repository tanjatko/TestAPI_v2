package megogo.responseProviderClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tv
{
    private Programme[] programme;

    private String sourceInfoURL;

    private String generatorInfoURL;

    private Channel channel;

    private String sourceInfoName;

    @XmlElement
    public Programme[] getProgramme ()
    {
        return programme;
    }

    public void setProgramme (Programme[] programme)
    {
        this.programme = programme;
    }

    @XmlAttribute
    public String getSourceInfoURL ()
{
    return sourceInfoURL;
}

    public void setSourceInfoURL (String sourceInfoURL)
{
    this.sourceInfoURL = sourceInfoURL;
}
    @XmlAttribute
    public String getGeneratorInfoURL ()
{
    return generatorInfoURL;
}

    public void setGeneratorInfoURL (String generatorInfoURL)
{
    this.generatorInfoURL = generatorInfoURL;
}
    @XmlElement
    public Channel getChannel ()
    {
        return channel;
    }

    public void setChannel (Channel channel)
    {
        this.channel = channel;
    }

    @XmlAttribute
    public String getSourceInfoName ()
{
    return sourceInfoName;
}

    public void setSourceInfoName (String sourceInfoName)
{
    this.sourceInfoName = sourceInfoName;
}

    @Override
    public String toString()
    {
        return "ClassPojo [programme = "+programme+", sourceInfoURL = "+sourceInfoURL+", generatorInfoURL = "+generatorInfoURL+", channel = "+channel+", sourceInfoName = "+sourceInfoName+"]";
    }
}

