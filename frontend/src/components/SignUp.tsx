import { Button, Center, FormControl, FormLabel, Heading, Input, Link, VStack } from '@chakra-ui/react';
import React from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import Section from './Section';

interface Inputs {
    username: string;
    password: string;
    confirmPassword: string;
}

const SignIn = (): JSX.Element => {
    const {
        register,
        handleSubmit,
        formState: { errors, isSubmitting },
    } = useForm<Inputs>();

    const onSubmit: SubmitHandler<Inputs> = (data) => {
        console.log(data);
    };

    return (
        <Center>
            <Section mt="10%" w="40%">
                <Heading mb="2rem" color="red.500">
                    Sign up
                </Heading>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <VStack spacing="1rem">
                        <FormControl>
                            <FormLabel>Brukernavn</FormLabel>
                            <Input
                                id="username"
                                placeholder="username"
                                {...register('username', {
                                    required: 'This is required',
                                })}
                            />
                        </FormControl>
                        <FormControl>
                            <FormLabel>Passord</FormLabel>
                            <Input
                                id="password"
                                placeholder="password"
                                type="password"
                                {...register('password', {
                                    required: 'This is required',
                                })}
                            />
                        </FormControl>
                        <FormControl>
                            <FormLabel>Bekreft Passord</FormLabel>
                            <Input
                                id="confirmPassword"
                                placeholder="password"
                                type="password"
                                {...register('confirmPassword', {
                                    required: 'This is required',
                                })}
                            />
                        </FormControl>
                        <Center>
                            <Button type="submit" colorScheme="red" isLoading={isSubmitting}>
                                Sign up
                            </Button>
                        </Center>
                    </VStack>
                </form>
                <Center my="1rem">
                    <p>or</p>
                </Center>
                <Center>
                    <Link color="red" href="/signin">
                        Sign in
                    </Link>
                </Center>
            </Section>
        </Center>
    );
};

export default SignIn;
