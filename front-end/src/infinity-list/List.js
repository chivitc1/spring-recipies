import React from 'react';
import './List.css';

const List = ({ list }) =>
    <div className="list">
        {
            list.map(item =>
                <div className="list-row" key={item.id}>
                    <a href={item.html_url}>{item.full_name}</a>
                </div>)
        }
    </div>

export default List;
