cmake_minimum_required(VERSION 3.22.1)

project("testproject")

set(NODE_MODULES_DIR "../../../../../node_modules")

include_directories(
        ${NODE_MODULES_DIR}/react-native/React
        ${NODE_MODULES_DIR}/react-native/React/Base
        ${NODE_MODULES_DIR}/react-native/ReactCommon/jsi
)

add_library(${CMAKE_PROJECT_NAME} SHARED
        "${NODE_MODULES_DIR}/react-native/ReactCommon/jsi/jsi/jsi.cpp"
        testproject.cpp
        Test.cpp
        TestBinding.cpp
        )

target_link_libraries(${CMAKE_PROJECT_NAME}
    android
    log
#    folly_runtime
    )
