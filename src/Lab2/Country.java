package Lab2;

public class Country implements Comparable<Country>{
    private String m_name = "";
    private int m_num = 0;
    private float m_total = 0;
    private float m_national = 0;

    public Country(String name, int num, float total, float national)
    {
        m_name = name;
        m_num = num;
        m_total = total;
        m_national = national;
    }

    @Override //ключевое слово, которое позволяет в дочернем классе заново создать реализацию метода родительского класса.
    public int compareTo(Country other)
    {
        float res = other.m_national - this.m_national;
        if (res < 0) return -1;
        else if(res > 0) return 1;
        else return 0;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public void setNum(int num)
    {
        m_num = num;
    }

    public void setTotal(float total)
    {
        m_total = total;
    }

    public void setNational(float national)
    {
        m_national = national;
    }

    public String getName()
    {
        return m_name;
    }

    public int getNum()
    {
        return m_num;
    }

    public float getTotal()
    {
        return m_total;
    }

    public float getNational()
    {
        return m_national;
    }

}
