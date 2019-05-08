import React, { Component } from 'react';
import SkyLight from 'react-skylight';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import './add-car.css';

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
                        <TextField label="Brand" placeholder="Brand" name="brand"
                            onChange={this.handleChange} /><br />
                        <TextField label="Model" placeholder="Model" name="model"
                            onChange={this.handleChange} /><br />
                        <TextField label="Color" placeholder="Color" name="color"
                            onChange={this.handleChange} /><br />
                        <TextField label="Year" placeholder="Year" name="year"
                            onChange={this.handleChange} /><br />
                        <TextField label="Price" placeholder="Price" name="price"
                            onChange={this.handleChange} /><br />
                        <div className="formButtons">
                            <Button variant="outlined" color="primary"
                                onClick={this.handleSubmit}>Save</Button>{'  '}
                            <Button variant="outlined" color="secondary"
                                onClick={this.cancelSubmit}>Cancel</Button>
                        </div>

                    </form>
                </SkyLight>
                <div>
                    <Button variant="contained" color="primary" style={{ 'margin': '10px' }}
                        onClick={() => this.refs.addDialog.show()}>New Car</Button>
                </div>
            </div>
        )
    }
}
