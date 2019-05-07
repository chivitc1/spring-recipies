import React, { Component } from 'react';
import SkyLight from 'react-skylight';

export default class AddCar extends Component {
    constructor(props) {
        super(props);
        this.state = { brand: '', model: '', year: '', color: '', price: '' };
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    handleSubmit = (event) => {
        event.preventDefault();
        let newCar = {
            brand: this.state.brand, 
            model: this.state.model, 
            color: this.state.color,
            year: this.state.year,
            price: this.state.price
        };
        this.props.addCar(newCar);
        this.refs.addDialog.hide();
    }

    cancelSubmit = (event) => {
        event.preventDefault();
        this.refs.addDialog.hide();
    }

    render() {
        return (
            <div>
                <SkyLight hideOnOverlayClicked ref="addDialog">
                    <h3>New Car</h3>
                    <form>
                        <input type="text" placeholder="Brand" name="brand"
                            onChange={this.handleChange} /><br />
                        <input type="text" placeholder="Model" name="model"
                            onChange={this.handleChange} /><br />
                        <input type="text" placeholder="Color" name="color"
                            onChange={this.handleChange} /><br />
                        <input type="text" placeholder="Year" name="year"
                            onChange={this.handleChange} /><br />
                        <input type="text" placeholder="Price" name="price"
                            onChange={this.handleChange} /><br />
                        <button onClick={this.handleSubmit}>Save</button>{'  '}
                        <button onClick={this.cancelSubmit}>Cancel</button><br />

                    </form>
                </SkyLight>
                <div>
                    <button style={{'margin': '10px'}}
                        onClick={() => this.refs.addDialog.show()}>New Car</button>
                </div>
            </div>
        )
    }
}
