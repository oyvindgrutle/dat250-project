import { Box } from '@chakra-ui/react';
import React from 'react';
import { Switch, Route, Router } from 'react-router-dom';
import CodeBox from './components/CodeBox';
import Header from './components/Header';
import Poll from './components/Poll';
import SignIn from './components/SignIn';

const App = (): JSX.Element => (
    <Box h="100vh" bgColor="red.100">
        <Header />
        <Switch>
            <Route path="/signin">
                <SignIn />
            </Route>
            <Route exact path="/polls">
                <Poll />
            </Route>
            <Route path="/polls/:id">
                <Poll />
            </Route>
            <Route exact path="/">
                <CodeBox />
            </Route>
        </Switch>
    </Box>
);

export default App;
