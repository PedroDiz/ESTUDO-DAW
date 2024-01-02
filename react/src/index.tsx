import * as React from 'react';
import { createRoot } from 'react-dom/client';
import { PeriodicFetcher } from './exercises/daw-2223-t1-comp';

const root = createRoot(document.getElementById('main-div'));
root.render(<PeriodicFetcher uri = {"https://httpbin.org/get"} time = {1000}/>);