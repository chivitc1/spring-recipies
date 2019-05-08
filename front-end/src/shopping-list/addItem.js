import React, { Component } from 'react';
// import ReactDOM from 'react-dom';
import SkyLight from 'react-skylight';
import RaisedButton from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

class AddItem extends Component {

    addItem = () => {
        const item = {product: this.state.product, amount: this.state.amount};
        this.props.addItem(item);
        this.addForm.hide();
    }

    handleChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
    }

    render() {
        return (
            <div>
                <section>
                    <RaisedButton onClick={() => this.addForm.show()} variant="raised" 
                    color="primary">Add Item</RaisedButton>
                </section>
                <SkyLight
                    hideOnOverlayClicked
                    ref={ref => this.addForm = ref}
                    title="Add item">
                    <TextField type="text" name="product" onChange={this.handleChange}
                        placeholder="product" /><br/>
                    <TextField type="text" name="amount" onChange={this.handleChange}
                        placeholder="amount" /><br/>
                    <RaisedButton onClick={this.addItem} 
                    variant="raised" 
                    color="primary">Add</RaisedButton>
                </SkyLight>
            </div>
        );
    }
}

export default AddItem;