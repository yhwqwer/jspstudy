package dto;

public class StudentDTO {

    private int stu_no;
    private String name;
    private int kor;
    private int eng;
    private int math;
    private double ave;
    private String mark;

    public static class Builder {
        private int stu_no;
        private String name;
        private int kor;
        private int eng;
        private int math;
        private double ave;
        private String mark;

        public Builder stu_no(int stu_no) {
            this.stu_no = stu_no;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder kor(int kor) {
            this.kor = kor;
            return this;
        }

        public Builder eng(int eng) {
            this.eng = eng;
            return this;
        }

        public Builder math(int math) {
            this.math = math;
            return this;
        }

        public Builder ave(double ave) {
            this.ave = ave;
            return this;
        }

        public Builder mark(String mark) {
            this.mark = mark;
            return this;
        }

        public StudentDTO build() {
            return new StudentDTO(this);
        }
    }

    private StudentDTO(Builder builder) {
        this.stu_no = builder.stu_no;
        this.name = builder.name;
        this.kor = builder.kor;
        this.eng = builder.eng;
        this.math = builder.math;
        this.ave = builder.ave;
        this.mark = builder.mark;
    }

    
    public int getStu_no() {
        return stu_no;
    }

    public void setStu_no(int stu_no) {
        this.stu_no = stu_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public double getAve() {
        return ave;
    }

    public void setAve(double ave) {
        this.ave = ave;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
