import React from "react";

const withPaginated = (Component) => (props) =>
    <div>
        <Component {...props} />

        <div className="interactions">
            {
                (props.page !== null && !props.isLoading && props.isError) &&
                <div>
                    <div>
                        Something went wrong...
                        <button
                            type="button"
                            onClick={props.onPaginatedSearch}>
                            Try again
                        </button>

                    </div>
                </div>
            }
        </div>
    </div>

export default withPaginated;