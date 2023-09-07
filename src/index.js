import { NativeModules, Platform ,View,requireNativeComponent} from 'react-native';
import PropTypes from 'prop-types';
import React from 'react';

const LINKING_ERROR =
  `The package 'react-native-migu-live' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const MiguLive = NativeModules.MiguLive
  ? NativeModules.MiguLive
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
);

const RCTLiveTextView = requireNativeComponent('RCTLiveView');

export class MyView extends React.Component {

  _onChange = (event: Event) => {
      const onChangeMessage = this.props.onChangeMessage;
      onChangeMessage && onChangeMessage(event.nativeEvent);
  }

  render() {
      return (
          <RCTLiveTextView {...this.props} onChange={this._onChange}/>
      );
  }
}

MyView.propTypes = {
  onChangeMessage:PropTypes.func,
};

export const multiply = (a, b) => {
  return MiguLive.multiply(a, b);
}
export const plus = (a, b) => {
    return MiguLive.plus(a, b);
}