import React from 'react';

class Hello extends React.Component {
    constructor(props) {
        super(props);
        this.state = {address: 'Hanoi, Vietnam'}
    }

    componentDidMount() {
        this.setState({address: 'Ho Chi Minh, Vietnam'});
    }
    render() {
        return (
            <div>
                <h1>Hello from {this.props.user}</h1>
                <h2>From my first React app</h2>
                <p>Address: {this.state.address}</p>
            </div>
        );
    }
}

export default Hello;