#pragma once

#include <jsi/jsi.h>

namespace example {

    class Test {
    private:
        friend class TestBinding;

        int runTest() const;
    };

} // namespace example
