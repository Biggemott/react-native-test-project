import {requireNativeComponent} from 'react-native';
import {ViewPropTypes} from 'deprecated-react-native-prop-types';

var viewProps = {
    name: 'SimpleDrawingView',
    propTypes: {
        ...ViewPropTypes,
        figure_rotation: ViewPropTypes.float
    }
}
module.exports = requireNativeComponent('SimpleDrawingView', viewProps);