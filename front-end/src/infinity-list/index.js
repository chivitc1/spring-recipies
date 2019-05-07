import React from 'react';
// import InfiniteScroll from 'react-infinite-scroll-component';
import './index.css';
import List from './List';
import withLoading from './loading';
import withMorePage from './morePage';
import withInfiniteScroll from './infiniteScroll';
import { compose } from 'recompose';

const applyUpdateResult = (result) => (prevState) => ({
    hits: [...prevState.hits, ...result.items],
    isLoading: false,
    isError: false,
});

const applySetResult = (result) => (prevState) => ({
    hits: result.items,
    isLoading: false,
    isError: false,
});

const applySetError = (prevState) => ({
    isError: true,
    isLoading: false,
  });

const getItemsUrl = (query, page) =>
    `https://api.github.com/search/repositories?q=${query}&page=${page}&per_page=50`;

// const ListWithLoadingWithMorePage = compose(
//     withMorePage,
//     withLoading,
// )(List);

// const ListWithLoadingWithInfiniteScroll = compose(
//     withInfiniteScroll,
//     withLoading
// )(List);

const AdvancedList = compose(
    withMorePage,
    withInfiniteScroll,
    withLoading
)(List);

class InfinityListDemo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            hits: [],
            page: 1,
            isLoading: false,
            isError: false,
        };
    }

    onInitialSearch = (e) => {
        e.preventDefault();
        const { value } = this.input;
        value && this.fetchItems(value, 0);
    }

    fetchItems = (query, page) => {
        this.setState({ isLoading: true });
        fetch(getItemsUrl(query, page))
            .then(response => response.json())
            .then(result => this.onSetResult(result, page))
            .catch(this.onSetError);
    }

    onSetResult = (result, page) => {
        page === 0
            ? this.setState(applySetResult(result))
            : this.setState(applyUpdateResult(result))
    }

    onPaginatedSearch = (e) => {
        const pageNum = this.state.page + 1;
        this.setState({ page: pageNum });
        this.fetchItems(this.input.value, pageNum);
    }

    onSetError = () => this.setState(applySetError);

    render() {
        return (
            <div className="App">
                <div className="interactions">
                    <form type="submit" onSubmit={this.onInitialSearch}>
                        <input type='text' ref={node => this.input = node} />
                        <button type="submit">Search</button>
                    </form>
                </div>

                <AdvancedList 
                    list={this.state.hits}
                    page={this.state.page}
                    isLoading={this.state.isLoading}
                    isError={this.state.isError}
                    onPaginatedSearch={this.onPaginatedSearch} />
            </div>
        );
    }
}

export default InfinityListDemo;
