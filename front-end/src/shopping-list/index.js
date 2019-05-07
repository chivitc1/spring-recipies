
import React, { Component } from 'react';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import AddItem from './addItem';

class ShoppingList extends Component {
    constructor(props) {
        super(props);
        this.state = { items: [] };
    }

    addItem = (item) => {
        this.setState({items: [item, ...this.state.items]});
    }
    render() {
        console.log()
        const listItems =  this.state.items.map((item, index) =>
            <ListItem key={index} >
                <ListItemText primary={item.product} secondary={item.amount} />            
            </ListItem>);
        return (
            <div className="App">
                <h2>Shopping List</h2>
                <AddItem addItem={this.addItem} />
                <List>{listItems}</List>
            </div>
        );
    }
}

export default ShoppingList;