#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_rosberry_arc_common_sample_repository_jni_JNIAdapter_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
