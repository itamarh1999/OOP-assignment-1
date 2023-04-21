public interface Scalar {
    Scalar add(Scalar s);
    Scalar add(ScalarInteger s);
    Scalar add(ScalarRational s);
    Scalar mul(Scalar s);
    Scalar mul(ScalarInteger s);
    Scalar mul(ScalarRational s);
    Scalar neg();
    Scalar power(int exponent);
    int sign();

}
