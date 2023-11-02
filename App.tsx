/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import type {PropsWithChildren} from 'react';
import {
  SafeAreaView,
  StatusBar,
  Button,
  useColorScheme,
  View,
  PixelRatio,
} from 'react-native';
import {useCallback, useRef} from 'react';

import Slider from "react-native-a11y-slider";

import SimpleDrawingView from "./ViewModule"

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? '#222' : '#F3F3F3',
  };

  const INITIAL_ROTATION = 0.0;

  const drawingViewRef = useRef<SimpleDrawingView>(null);


  let sliderValue = 0;

  const onSliderChange = useCallback((values) => {
    drawingViewRef.current?.setNativeProps({figure_rotation: values[0]});
    sliderValue = values[0]
  }, []);

  return (
    <SafeAreaView
        backgroundColor={backgroundStyle.backgroundColor}
        style={{
            height: '100%',
            width: '100%',
            justifyContent: 'center',
            alignItems: 'center'
        }}>

        <StatusBar
            barStyle={isDarkMode ? 'light-content' : 'dark-content'}
            backgroundColor={backgroundStyle.backgroundColor}
        />

        <SimpleDrawingView
            ref={drawingViewRef}
            style={{
                height: PixelRatio.getPixelSizeForLayoutSize(100),
                width: PixelRatio.getPixelSizeForLayoutSize(100),
            }}
            figure_rotation={INITIAL_ROTATION}
        />

        <Slider
            style={{
                paddingLeft: PixelRatio.getPixelSizeForLayoutSize(8),
                paddingRight: PixelRatio.getPixelSizeForLayoutSize(8)
            }}
            min={0} max={360} values={[INITIAL_ROTATION]}
            onChange={onSliderChange}
        />

        <View
            style={{
                paddingTop: PixelRatio.getPixelSizeForLayoutSize(8)
            }}>
            <Button
                title="Press me"
                onPress={() => {
                    console.warn(global.nativeTest.runTest(sliderValue))
                }}
            />
        </View>

    </SafeAreaView>
  );
}

export default App;
